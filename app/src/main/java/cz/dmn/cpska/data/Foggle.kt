package cz.dmn.cpska.data

enum class Foggle(val key: String, val defaultEnabled: Boolean = false) {
    PROFILE_SECTION("profile-section");

    var isEnabled: Boolean = defaultEnabled
}