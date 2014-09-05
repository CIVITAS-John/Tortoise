// (C) Uri Wilensky. https://github.com/NetLogo/Tortoise

package org.nlogo.tortoise

import org.nlogo.{ nvm, prim }

object SimplePrims {

  object SimpleReporter {
    def unapply(r: nvm.Reporter): Option[String] =
      PartialFunction.condOpt(r) {
        case _: prim._nobody               => "Nobody"
        case _: prim.etc._nopatches        => "new PatchSet([])"
        case _: prim.etc._noturtles        => "new TurtleSet([])"
        case _: prim.etc._nolinks          => "new LinkSet([])"
        case _: prim.etc._minpxcor         => "world.topology.minPxcor"
        case _: prim.etc._minpycor         => "world.topology.minPycor"
        case _: prim.etc._maxpxcor         => "world.topology.maxPxcor"
        case _: prim.etc._maxpycor         => "world.topology.maxPycor"
        case _: prim.etc._linkneighbors    => "LinkPrims.linkNeighbors(false, false)"
        case _: prim.etc._inlinkneighbors  => "LinkPrims.linkNeighbors(true, false)"
        case _: prim.etc._outlinkneighbors => "LinkPrims.linkNeighbors(true, true)"
        case _: prim.etc._mylinks          => "LinkPrims.connectedLinks(false, false)"
        case _: prim.etc._myinlinks        => "LinkPrims.connectedLinks(true, false)"
        case _: prim.etc._myoutlinks       => "LinkPrims.connectedLinks(true, true)"
        case _: prim.etc._mousedown        => "notImplemented('mouse-down?', false)()"
        case _: prim.etc._mouseinside      => "notImplemented('mouse-inside?', false)()"
        case _: prim.etc._plotxmin         => "notImplemented('plot-x-min', 0)()"
        case _: prim.etc._plotxmax         => "notImplemented('plot-x-max', 0)()"
        case _: prim.etc._plotymin         => "notImplemented('plot-y-min', 0)()"
        case _: prim.etc._plotymax         => "notImplemented('plot-y-max', 0)()"
        case _: prim.etc._mousexcor        => "notImplemented('mouse-xcor', 0)()"
        case _: prim.etc._mouseycor        => "notImplemented('mouse-ycor', 0)()"
        case _: prim.etc._worldwidth       => "world.topology.width"
        case _: prim.etc._worldheight      => "world.topology.height"
      }
  }

  object InfixReporter {
    def unapply(r: nvm.Reporter): Option[String] =
      PartialFunction.condOpt(r) {
        case _: prim.etc._plus           => "+"
        case _: prim._minus              => "-"
        case _: prim.etc._mult           => "*"
        case _: prim.etc._div            => "/"
        case _: prim._and                => "&&"
        case _: prim._or                 => "||"
        case _: prim.etc._xor            => "!="
      }
  }

  object NormalReporter {
    def unapply(r: nvm.Reporter): Option[String] =
      PartialFunction.condOpt(r) {
        case _: prim._lessthan               => "Prims.lt"
        case _: prim._greaterthan            => "Prims.gt"
        case _: prim.etc._greaterorequal     => "Prims.gte"
        case _: prim.etc._lessorequal        => "Prims.lte"
        case _: prim.etc._turtle             => "world.turtleManager.getTurtle"
        case _: prim.etc._patch              => "world.getPatchAt"
        case _: prim._neighbors              => "SelfPrims.getNeighbors"
        case _: prim._neighbors4             => "SelfPrims.getNeighbors4"
        case _: prim._equal                  => "Prims.equality"
        case _: prim._notequal               => "!Prims.equality"
        case _: prim.etc._self               => "SelfManager.self"
        case _: prim.etc._myself             => "SelfManager.myself"
        case _: prim._turtles                => "world.turtles"
        case _: prim.etc._links              => "world.links"
        case _: prim._patches                => "world.patches"
        case _: prim.etc._ticks              => "world.ticker.tickCount"
        case _: prim.etc._timer              => "workspace.timer.elapsed"
        case _: prim._list                   => "ListPrims.list"
        case _: prim.etc._item               => "ListPrims.item"
        case _: prim.etc._first              => "ListPrims.first"
        case _: prim.etc._last               => "ListPrims.last"
        case _: prim.etc._fput               => "ListPrims.fput"
        case _: prim.etc._lput               => "ListPrims.lput"
        case _: prim.etc._butfirst           => "ListPrims.butFirst"
        case _: prim.etc._butlast            => "ListPrims.butLast"
        case _: prim.etc._sort               => "ListPrims.sort"
        case _: prim.etc._reverse            => "ListPrims.reverse"
        case _: prim.etc._max                => "ListPrims.max"
        case _: prim.etc._length             => "ListPrims.length"
        case _: prim.etc._min                => "ListPrims.min"
        case _: prim.etc._mean               => "ListPrims.mean"
        case _: prim._sum                    => "ListPrims.sum"
        case _: prim.etc._map                => "Tasks.map"
        case _: prim.etc._abs                => "StrictMath.abs"
        case _: prim.etc._pow                => "StrictMath.pow"
        case _: prim._random                 => "Prims.random"
        case _: prim.etc._randomfloat        => "Prims.randomFloat"
        case _: prim.etc._randomxcor         => "world.topology.randomXcor"
        case _: prim.etc._randomycor         => "world.topology.randomYcor"
        case _: prim._oneof                  => "ListPrims.oneOf"
        case _: prim.etc._nof                => "ListPrims.nOf"
        case _: prim.etc._removeduplicates   => "ListPrims.removeDuplicates"
        case _: prim.etc._patchset           => "Prims.patchSet"
        case _: prim.etc._distance           => "SelfManager.self().distance"
        case _: prim.etc._distancexy         => "SelfManager.self().distanceXY"
        case _: prim._inradius               => "SelfManager.self().inRadius"
        case _: prim.etc._towards            => "SelfManager.self().towards"
        case _: prim.etc._towardsxy          => "SelfManager.self().towardsXY"
        case _: prim._patchat                => "SelfManager.self().patchAt"
        case _: prim.etc._patchahead         => "SelfManager.self().patchAhead"
        case _: prim.etc._patchrightandahead => "SelfManager.self().patchRightAndAhead"
        case _: prim.etc._patchleftandahead  => "SelfManager.self().patchLeftAndAhead"
        case _: prim.etc._canmove            => "SelfManager.self().canMove"
        case _: prim.etc._shadeof            => "ColorModel.areRelatedByShade"
        case _: prim.etc._scalecolor         => "ColorModel.scaleColor"
        case _: prim.etc._patchhere          => "SelfManager.self().getPatchHere"
        case _: prim.etc._turtleshere        => "SelfManager.self().turtlesHere"
        case _: prim.etc._turtleson          => "Prims.turtlesOn"
        case _: prim.etc._turtlesat          => "SelfManager.self().turtlesAt"
        case _: prim._other                  => "SelfPrims.other"
        case _: prim.etc._sin                => "Trig.unsquashedSin"
        case _: prim.etc._cos                => "Trig.unsquashedCos"
        case _: prim.etc._atan               => "Trig.atan"
        case _: prim.etc._floor              => "StrictMath.floor"
        case _: prim.etc._ceil               => "StrictMath.ceil"
        case _: prim.etc._int                => "Prims.toInt"
        case _: prim.etc._round              => "StrictMath.round"
        case _: prim.etc._precision          => "Prims.precision"
        case _: prim.etc._link               => "world.linkManager.getLink"
        case _: prim.etc._linkneighbor       => "LinkPrims.isLinkNeighbor(false, false)"
        case _: prim.etc._inlinkneighbor     => "LinkPrims.isLinkNeighbor(true, false)"
        case _: prim.etc._outlinkneighbor    => "LinkPrims.isLinkNeighbor(true, true)"
        case _: prim.etc._inlinkfrom         => "LinkPrims.findLinkViaNeighbor(true, false)"
        case _: prim.etc._outlinkto          => "LinkPrims.findLinkViaNeighbor(true, true)"
        case _: prim.etc._linkwith           => "LinkPrims.findLinkViaNeighbor(false, false)"
        case _: prim.etc._bothends           => "SelfManager.self().bothEnds"
        case _: prim.etc._otherend           => "SelfManager.self().otherEnd"
        case _: prim.etc._sqrt               => "StrictMath.sqrt"
        case _: prim.etc._mod                => "Prims.mod"
        case _: prim.etc._empty              => "ListPrims.empty"
        case _: prim.etc._isreportertask     => "Tasks.isReporterTask"
        case _: prim.etc._iscommandtask      => "Tasks.isCommandTask"
        case _: prim.etc._dx                 => "SelfManager.self().dx"
        case _: prim.etc._dy                 => "SelfManager.self().dy"
        case _: prim.etc._subtractheadings   => "Prims.subtractHeadings"
        case _: prim.etc._boom               => "Prims.boom"
        case _: prim.etc._member             => "ListPrims.member"
        case _: prim.etc._position           => "ListPrims.position"
        case _: prim.etc._remove             => "ListPrims.remove"
        case _: prim.etc._removeitem         => "ListPrims.removeItem"
        case _: prim.etc._replaceitem        => "ListPrims.replaceItem"
        case _: prim.etc._sublist            => "ListPrims.sublist"
        case _: prim._sentence               => "ListPrims.sentence"
        case _: prim.etc._substring          => "ListPrims.substring"
        case _: prim.etc._exp                => "StrictMath.exp"
        case _: prim.etc._variance           => "ListPrims.variance"
        case _: prim.etc._subject            => "world.observer.subject"
      }
  }

  object SimpleCommand {
    def unapply(c: nvm.Command): Option[String] =
      PartialFunction.condOpt(c) {
        case _: prim._done             => ""
        case _: prim.etc._observercode => ""
        case _: prim.etc._stop         => "throw new Exception.StopInterrupt"
        case _: prim.etc._hideturtle   => "SelfManager.self().hideTurtle(true);"
        case _: prim.etc._showturtle   => "SelfManager.self().hideTurtle(false);"
      }
  }

  object NormalCommand {
    def unapply(c: nvm.Command): Option[String] =
      PartialFunction.condOpt(c) {
        case _: prim.etc._outputprint       => "Prims.outputPrint"
        case _: prim.etc._clearall          => "world.clearAll"
        case _: prim.etc._clearpatches      => "world.clearPatches"
        case _: prim.etc._clearturtles      => "world.turtleManager.clearTurtles"
        case _: prim.etc._clearticks        => "world.ticker.clear"
        case _: prim.etc._resizeworld       => "world.resize"
        case _: prim.etc._resetticks        => "world.ticker.reset"
        case _: prim.etc._resettimer        => "workspace.timer.reset"
        case _: prim.etc._tick              => "world.ticker.tick"
        case _: prim.etc._tickadvance       => "world.ticker.tickAdvance"
        case _: prim.etc._moveto            => "SelfManager.self().moveTo"
        case _: prim.etc._face              => "SelfManager.self().face"
        case _: prim.etc._facexy            => "SelfManager.self().faceXY"
        case _: prim.etc._pendown           => "SelfManager.self().penManager.lowerPen"
        case _: prim.etc._penup             => "SelfManager.self().penManager.raisePen"
        case _: prim.etc._tie               => "SelfManager.self().tie"
        case _: prim.etc._untie             => "SelfManager.self().untie"
        case _: prim._fd                    => "SelfPrims.fd"
        case _: prim._bk                    => "SelfPrims.bk"
        case _: prim._jump                  => "SelfPrims.jump"
        case _: prim.etc._left              => "SelfPrims.left"
        case _: prim.etc._right             => "SelfPrims.right"
        case _: prim.etc._setxy             => "SelfPrims.setXY"
        case _: prim.etc._die               => "SelfPrims.die"
        case _: prim.etc._randomseed        => "Random.setSeed"
        case _: prim.etc._setcurrentplot    => "notImplemented('set-current-plot', undefined)"
        case _: prim.etc._setcurrentplotpen => "notImplemented('set-current-plot-pen', undefined)"
        case _: prim.etc._plot              => "notImplemented('plot', undefined)"
        case _: prim.etc._plotxy            => "notImplemented('plotxy', undefined)"
        case _: prim.etc._histogram         => "notImplemented('histogram', undefined)"
        case _: prim.etc._setplotxrange     => "notImplemented('set-plot-x-range', undefined)"
        case _: prim.etc._setplotyrange     => "notImplemented('set-plot-y-range', undefined)"
        case _: prim.etc._sethistogramnumbars => "notImplemented('set-histrogram-num-bars', undefined)"
        case _: prim.etc._clearallplots     => "notImplemented('clear-all-plots', undefined)"
        case _: prim.etc._plotpenreset      => "notImplemented('plot-pen-reset', undefined)"
        case _: prim.etc._plotpendown       => "notImplemented('plot-pen-down', undefined)"
        case _: prim.etc._plotpenup         => "notImplemented('plot-pen-up', undefined)"
        case _: prim.etc._updateplots       => "notImplemented('update-plots', undefined)"
        case _: prim.etc._display           => "notImplemented('display', undefined)"
        case _: prim.etc._stamp             => "notImplemented('stamp', undefined)"
        case _: prim.etc._usermessage       => "notImplemented('user-message', undefined)"
        case _: prim.etc._follow            => "world.observer.follow"
        case _: prim.etc._ride              => "world.observer.ride"
        case _: prim.etc._watch             => "world.observer.watch"
        case _: prim.etc._watchme           => "SelfManager.self().watchMe"
        case _: prim.etc._resetperspective  => "world.observer.resetPerspective"
        case _: prim.etc._layoutspring      => "LayoutManager.layoutSpring"
      }
  }

}
