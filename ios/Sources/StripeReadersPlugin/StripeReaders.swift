import Foundation

@objc public class StripeReaders: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
