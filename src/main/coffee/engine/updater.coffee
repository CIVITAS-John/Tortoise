#@# Make an `Update` class that always has turtles, links, and patches
#@# Vassal: { id: ID, companion: { trackedKeys: Set }, registerUpdate: Array[String -> Value] }
#@# Overlord: { updates: Array[Update], flushUpdates: Unit, collectUpdates: Array[Update] }
define(['engine/exception', 'engine/link', 'engine/observer', 'engine/patch', 'engine/turtle', 'engine/world']
     , ( Exception,          Link,          Observer,          Patch,          Turtle,          World) ->

  ignored = ["", ""]

  class Updater

    @_updates = undefined

    constructor: ->
      @_updates = [{turtles: {}, patches: {}, links: {}, observer: {}, world: {}}]

    collectUpdates: ->
      result =
        if @_updates.length is 0 #@# `isEmpty`?
          [turtles: {}, patches: {}]
        else
          @_updates
      @_updates = [{turtles: {}, patches: {}, links: {}, observer: {}, world: {}}]
      result

    update: (agentType, id, newAgent) ->
      @_updates[0][agentType][id] = newAgent

    updated: (obj) => (vars...) => #@# Polymorphize correctly
      update = @_updates[0]

      [entry, objMap] =
        if obj instanceof Turtle
          [update.turtles, @_turtleMap(obj)]
        else if obj instanceof Patch
          [update.patches, @_patchMap(obj)]
        else if obj instanceof Link
          [update.links, @_linkMap(obj)]
        else if obj instanceof World
          [update.world, @_worldMap(obj)]
        else if obj instanceof Observer
          [update.observer, @_observerMap(obj)]
        else
          throw new Exception.NetLogoException("Unrecognized update type")

      entryUpdate = entry[obj.id] or {}

      # Receiving updates for a turtle that's about to die means the turtle was
      # reborn, so we revive it in the update - BH 1/13/2014
      if entryUpdate['WHO'] < 0
        delete entryUpdate['WHO']

      for v in vars
        mapping = objMap[v]
        if mapping?
          if mapping isnt ignored
            [varName, value]     = mapping
            entryUpdate[varName] = value
            entry[obj.id]        = entryUpdate
        else
          throw new Exception.NetLogoException("Unknown #{obj.constructor.name} variable for update: #{v}")

      return


    # (Turtle) => Object[String, (String, Any)]
    _turtleMap: (turtle) -> {
      breed:         ["BREED",       turtle.getBreedName()]
      color:         ["COLOR",       turtle.color]
      heading:       ["HEADING",     turtle.heading]
      id:            ["WHO",         turtle.id]
      'label-color': ["LABEL-COLOR", turtle.labelcolor]
      'hidden?':     ["HIDDEN?",     turtle.hidden]
      label:         ["LABEL",       turtle.label.toString()]
      'pen-size':    ["PEN-SIZE",    turtle.penManager.getSize()]
      'pen-mode':    ["PEN-MODE",    turtle.penManager.getMode().toString()]
      shape:         ["SHAPE",       turtle.shape]
      size:          ["SIZE",        turtle.size]
      xcor:          ["XCOR",        turtle.xcor()]
      ycor:          ["YCOR",        turtle.ycor()]
    }

    # (Patch) => Object[String, (String, Any)]
    _patchMap: (patch) -> {
      id:             ["WHO",          patch.id]
      pcolor:         ["PCOLOR",       patch._pcolor]
      plabel:         ["PLABEL",       patch._plabel.toString()]
      'plabel-color': ["PLABEL-COLOR", patch._plabelcolor]
      pxcor:          ["PXCOR",        patch.pxcor]
      pycor:          ["PYCOR",        patch.pycor]
    }

    # (Link) => Object[String, (String, Any)]
    _linkMap: (link) -> {
      breed:         ["BREED",       link.getBreedName()]
      color:         ["COLOR",       link._color]
      end1:          ["END1",        link.end1.id]
      end2:          ["END2",        link.end2.id]
      heading:       ["HEADING",     link.getHeading()]
      'hidden?':     ["HIDDEN?",     link._isHidden]
      id:            ["ID",          link.id]
      label:         ["LABEL",       link._label.toString()]
      'label-color': ["LABEL-COLOR", link._labelcolor]
      midpointx:     ["MIDPOINTX",   link.getMidpointX()]
      midpointy:     ["MIDPOINTY",   link.getMidpointY()]
      shape:         ["SHAPE",       link._shape]
      size:          ["SIZE",        link.getSize()]
      thickness:     ["THICKNESS",   link._thickness]
      'tie-mode':    ["TIE-MODE",    link._tiemode]
      lcolor:        ignored
      llabel:        ignored
      llabelcolor:   ignored
      lhidden:       ignored
      lbreed:        ignored
      lshape:        ignored
    }

    # (World) => Object[String, (String, Any)]
    _worldMap: (world) -> {
      height:                     ["worldHeight",               world.height()]
      id:                         ["WHO",                       world.id]
      patchesAllBlack:            ["patchesAllBlack",           world._patchesAllBlack]
      patchesWithLabels:          ["patchesWithLabels",         world._patchesWithLabels]
      maxPxcor:                   ["MAXPXCOR",                  world.maxPxcor]
      maxPycor:                   ["MAXPYCOR",                  world.maxPycor]
      minPxcor:                   ["MINPXCOR",                  world.minPxcor]
      minPycor:                   ["MINPYCOR",                  world.minPycor]
      ticks:                      ["ticks",                     world.ticker._count]
      unbreededLinksAreDirected:  ["unbreededLinksAreDirected", world.unbreededLinksAreDirected]
      width:                      ["worldWidth",                world.width()]
    }

    # (Observer) => Object[String, (String, Any)]
    _observerMap: (observer) -> {
      id:          ["WHO",         observer.id]
      perspective: ["perspective", observer._perspective]
      targetAgent: ["targetAgent", observer._targetAgent]
    }

)
