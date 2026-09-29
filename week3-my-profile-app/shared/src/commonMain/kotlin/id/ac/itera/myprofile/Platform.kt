package id.ac.itera.myprofile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform