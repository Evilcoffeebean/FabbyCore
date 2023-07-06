package dev.fabby.com.libraries.gui.components

import org.bukkit.Bukkit
import java.util.*

/**
 * @author GabyTM
 */
enum class ServerVersion
/**
 * TarvosCore constructor that defines a protocol version representing NX where N is the TarvosCore version and X is the R version
 * For example NX - 81 - 8_R1
 *
 * @param versionNumber The protocol version
 */(private val versionNumber: Int) {
    UNKNOWN(0),

    /**
     * Legacy versions
     */
    V1_8_R1(81),
    V1_8_R2(82),
    V1_8_R3(83),
    V1_9_R1(91),
    V1_9_R2(92),
    V1_10_R1(101),
    V1_11_R1(111),
    V1_12_R1(121),

    /**
     * New versions
     */
    V1_13_R1(131),
    V1_13_R2(132),
    V1_14_R1(141),
    V1_15_R1(151),
    V1_16_R1(161);

    /**
     * Checks if the current version is newer than the [ServerVersion] specified
     *
     * @param version The [ServerVersion] to check from
     * @return Whether the version is newer
     */
    fun isNewerThan(version: ServerVersion): Boolean {
        return versionNumber > version.versionNumber
    }

    /**
     * Checks if the current version is older than the [ServerVersion] specified
     *
     * @param version The [ServerVersion] to check from
     * @return Whether the version is older
     */
    fun isOlderThan(version: ServerVersion): Boolean {
        return versionNumber < version.versionNumber
    }

    val isLegacy: Boolean
        /**
         * Checks if the server is using a legacy version
         *
         * @return Whether the server is running on a version before 1.13
         */
        get() = versionNumber < V1_13_R1.versionNumber

    companion object {
        private val PACKAGE_NAME = Bukkit.getServer().javaClass.getPackage().name
        private val NMS_VERSION = PACKAGE_NAME.substring(PACKAGE_NAME.lastIndexOf('.') + 1)
        val CURRENT_VERSION = getByNmsName(NMS_VERSION)

        /**
         * Gets a version from the NMS name
         *
         * @param name The NMS name
         * @return The [ServerVersion] that represents that version
         */
        private fun getByNmsName(name: String?): ServerVersion {
            return Arrays.stream(entries.toTypedArray())
                .filter { version: ServerVersion -> version.name.equals(name, ignoreCase = true) }
                .findFirst()
                .orElse(UNKNOWN)
        }
    }
}