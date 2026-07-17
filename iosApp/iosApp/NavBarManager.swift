import Foundation

extension NSNotification.Name {
    static let navBarStateChanged = NSNotification.Name("NavBarStateChanged")
}

struct NavBarState {
    let selectedIndex: Int
    let isVisible: Bool
}
