package config.loadbalancer

import io.grpc.LoadBalancerRegistry
import io.grpc.internal.PickFirstLoadBalancerProvider

fun setupLoadBalancer() {
    LoadBalancerRegistry.getDefaultRegistry().register(PickFirstLoadBalancerProvider())
}