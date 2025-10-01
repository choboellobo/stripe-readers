// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "StripeReaders",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "StripeReaders",
            targets: ["StripeReadersPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "StripeReadersPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/StripeReadersPlugin"),
        .testTarget(
            name: "StripeReadersPluginTests",
            dependencies: ["StripeReadersPlugin"],
            path: "ios/Tests/StripeReadersPluginTests")
    ]
)