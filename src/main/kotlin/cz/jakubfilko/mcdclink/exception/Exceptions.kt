package cz.jakubfilko.mcdclink.exception

open class McDcLinkException(message: String? = null) : RuntimeException(message)

class UserNotFoundException(message: String) : McDcLinkException(message)

class UserIdAlreadySetException(message: String? = null) : McDcLinkException(message)

class UserIdCannotBeChangedException(message: String) : McDcLinkException(message)
