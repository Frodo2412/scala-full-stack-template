import sbt.*

object dependencies {

    object CatsEffect {

        private lazy val catsEffectVersion = "3.5.0"

        lazy val effect: ModuleID =
            "org.typelevel" %% "cats-effect" % catsEffectVersion

    }

    object Circe {

        private lazy val circeVersion = "0.14.5"

        lazy val generic = "io.circe" %% "circe-generic" % circeVersion
        lazy val fs2     = "io.circe" %% "circe-fs2"     % circeVersion

    }

    object Ciris {
        private lazy val cirisVersion = "3.4.0"

        lazy val ciris: ModuleID = "is.cir" %% "ciris" % cirisVersion
    }

    object Fuuid {
        private lazy val fuuidVersion = "0.8.0-M2"

        lazy val fuuid: ModuleID = "io.chrisdavenport" %% "fuuid" % fuuidVersion
    }

    object Http4s {

        private lazy val http4sVersion = "0.23.23"

        lazy val dsl: ModuleID    = "org.http4s" %% "http4s-dsl" % http4sVersion
        lazy val client: ModuleID =
            "org.http4s" %% "http4s-ember-client" % http4sVersion
        lazy val server: ModuleID =
            "org.http4s" %% "http4s-ember-server" % http4sVersion
        lazy val circe: ModuleID =
            "org.http4s" %% "http4s-circe" % http4sVersion

    }

    object Natchez {

        private val natchezVersion = "0.3.1"

        lazy val core = "org.tpolecat" %% "natchez-core" % natchezVersion
    }

    object Scribe {

        private lazy val scribeVersion = "3.12.2"

        lazy val core: ModuleID = "com.outr" %% "scribe"      % scribeVersion
        lazy val cats: ModuleID = "com.outr" %% "scribe-cats" % scribeVersion

    }

    object Skunk {

        private lazy val skunkVersion = "0.6.0"

        lazy val skunk: ModuleID = "org.tpolecat" %% "skunk-core" % skunkVersion

    }

    object Smithy4s {

        private lazy val smithy4sVersion = "0.18.0"

        lazy val smithy4s: ModuleID =
            "com.disneystreaming.smithy4s" %% "smithy4s-http4s" % smithy4sVersion
        lazy val smithy4sSwagger: ModuleID =
            "com.disneystreaming.smithy4s" %% "smithy4s-http4s-swagger" % smithy4sVersion

    }

    object Tyrian {

        lazy val tyrianVersion = "0.8.0"

        lazy val tyrian: ModuleID =
            "io.indigoengine" %% "tyrian" % tyrianVersion

    }

    object Weaver {

        private lazy val weaverVersion = "0.8.1"

        lazy val weaver: ModuleID =
            "com.disneystreaming" %% "weaver-cats" % weaverVersion % Test

    }

}
