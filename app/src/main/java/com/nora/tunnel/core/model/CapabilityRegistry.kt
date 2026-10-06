package com.nora.tunnel.core.model

data class CoreCapability(
    val core: Core,
    val protocols: Set<Protocol>,
    val transports: Set<Transport>
)

object CapabilityRegistry {

    val registry = mapOf(
        Core.XRAY to CoreCapability(
            Core.XRAY,
            setOf(
                Protocol.VLESS,
                Protocol.VMESS,
                Protocol.TROJAN,
                Protocol.SHADOWSOCKS
            ),
            setOf(
                Transport.TCP,
                Transport.WS,
                Transport.GRPC,
                Transport.QUIC,
                Transport.H3
            )
        ),

        Core.SINGBOX to CoreCapability(
            Core.SINGBOX,
            setOf(
                Protocol.VLESS,
                Protocol.VMESS,
                Protocol.TROJAN,
                Protocol.SHADOWSOCKS,
                Protocol.HYSTERIA2,
                Protocol.TUIC
            ),
            setOf(
                Transport.TCP,
                Transport.UDP,
                Transport.WS,
                Transport.GRPC,
                Transport.H2,
                Transport.QUIC,
                Transport.H3
            )
        ),

        Core.WIREGUARD to CoreCapability(
            Core.WIREGUARD,
            setOf(Protocol.WIREGUARD),
            setOf(Transport.UDP)
        ),

        Core.OPENVPN to CoreCapability(
            Core.OPENVPN,
            setOf(Protocol.OPENVPN),
            setOf(
                Transport.TCP,
                Transport.UDP
            )
        ),

        Core.IKEV2 to CoreCapability(
            Core.IKEV2,
            setOf(Protocol.IKEV2),
            setOf(Transport.UDP)
        ),

        Core.SSH to CoreCapability(
            Core.SSH,
            setOf(Protocol.SSH),
            setOf(Transport.TCP)
        )
    )

    fun isValid(
        core: Core,
        protocol: Protocol,
        transport: Transport
    ): Boolean {
        val capability = registry[core] ?: return false

        return protocol in capability.protocols &&
                transport in capability.transports
    }
}
