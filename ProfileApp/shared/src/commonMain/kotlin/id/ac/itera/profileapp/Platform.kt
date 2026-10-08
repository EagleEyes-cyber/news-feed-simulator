package id.ac.itera.profileapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform