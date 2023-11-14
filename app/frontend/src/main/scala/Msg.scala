package com.example.app.ui

sealed trait Msg

object Msg:

    case object NoOp                       extends Msg
    case class GetStatus(status: Int)      extends Msg
    case class NetworkError(error: String) extends Msg

end Msg
