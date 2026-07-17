import SwiftUI
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        IosBackgroundTaskManager.shared.register()
        IosBackgroundTaskManager.shared.schedule()
        return true
    }
}

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    @StateObject private var toastManager = ToastManager()
    @State private var tabBarWindow: TabBarOverlayWindow?

    init() {
        PlatformUtils_iosKt.swiftToastHandler = { message in
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: NSNotification.Name("ShowToast"), object: message)
            }
        }

        IosNavBridge.shared.onStateChange = { index, visible in
            DispatchQueue.main.async {
                let state = NavBarState(selectedIndex: index.intValue, isVisible: visible.boolValue)
                NotificationCenter.default.post(name: .navBarStateChanged, object: state)
            }
        }
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
                .ignoresSafeArea()
                .onOpenURL { url in
                    IosAuthDeepLink.shared.handle(urlString: url.absoluteString)
                }
                .onReceive(NotificationCenter.default.publisher(for: NSNotification.Name("ShowToast"))) { notification in
                    if let message = notification.object as? String {
                        toastManager.show(message: message)
                    }
                }
                .overlay(alignment: .top) {
                    if toastManager.isPresented {
                        HStack {
                            if let icon = toastManager.systemImageName {
                                Image(systemName: icon)
                                    .foregroundStyle(toastManager.tint != nil ? .white : Color(uiColor: .label))
                            }
                            Text(toastManager.message)
                                .font(.subheadline.weight(.semibold))
                                .foregroundStyle(toastManager.tint != nil ? .white : Color(uiColor: .label))
                        }
                        .padding(.horizontal, 24)
                        .padding(.vertical)
                        .modifier(ToastGlass(tint: toastManager.tint))
                        .transition(.move(edge: .top).combined(with: .opacity))
                    }
                }
                .animation(.spring(response: 0.4, dampingFraction: 0.85), value: toastManager.isPresented)
                .onReceive(NotificationCenter.default.publisher(for: UIScene.didActivateNotification)) { note in
                    guard tabBarWindow == nil,
                          let scene = note.object as? UIWindowScene else { return }
                    let window = TabBarOverlayWindow(windowScene: scene)
                    let tabVC = KitoTabBarController()
                    window.rootViewController = tabVC
                    _ = tabVC.view
                    window.backgroundColor = .clear
                    window.isOpaque = false
                    window.windowLevel = .normal + 1
                    window.alpha = 0
                    window.isHidden = false
                    tabBarWindow = window
                }
        }
    }
}
