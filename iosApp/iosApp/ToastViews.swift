import SwiftUI

struct ToastPresenter: ViewModifier {
    @Binding var isPresented: Bool
    let duration: TimeInterval
    let systemImageName: String?
    let message: String
    let tint: Color?

    func body(content: Content) -> some View {
        content
            .overlay(alignment: .top) {
                if isPresented {
                    HStack {
                        if let systemImageName {
                            Image(systemName: systemImageName)
                                .foregroundStyle(tint != nil ? .white : Color(uiColor: .label))
                        }
                        Text(message)
                            .font(.subheadline.weight(.semibold))
                            .foregroundStyle(tint != nil ? .white : Color(uiColor: .label))
                    }
                    .padding(.horizontal, 24)
                    .padding(.vertical)
                    .modifier(ToastGlass(tint: tint))
                    .transition(.move(edge: .top).combined(with: .opacity))
                    .onAppear {
                        DispatchQueue.main.asyncAfter(deadline: .now() + duration) {
                            withAnimation {
                                isPresented = false
                            }
                        }
                    }
                }
            }
            .animation(.default, value: isPresented)
    }
}

struct ToastGlass: ViewModifier {
    let tint: Color?

    func body(content: Content) -> some View {
        if #available(iOS 26.0, *) {
            if let tint {
                content.glassEffect(.regular.tint(tint), in: .capsule)
            } else {
                content.glassEffect(.regular, in: .capsule)
            }
        } else {
            content.frostedGlassEffect(.regular.tint(tint))
        }
    }
}

struct FrostedGlass {
    var style: UIBlurEffect.Style = .systemUltraThinMaterial
    var tint: Color? = nil

    static let regular = FrostedGlass(style: .systemMaterial)

    func tint(_ color: Color?) -> FrostedGlass {
        var copy = self
        copy.tint = color
        return copy
    }
}

extension View {
    func frostedGlassEffect(_ glass: FrostedGlass) -> some View {
        self.background(
            ZStack {
                VisualEffectView(style: glass.style)
                    .mask(Capsule())

                if let tint = glass.tint {
                    tint.opacity(0.1)
                        .clipShape(Capsule())
                }

                Capsule()
                    .strokeBorder(Color.white.opacity(0.1), lineWidth: 0.5)
            }
            .shadow(color: .black.opacity(0.15), radius: 15, x: 0, y: 8)
        )
    }

    func toast(
        isPresented: Binding<Bool>,
        duration: TimeInterval = 3.0,
        systemImageName: String? = nil,
        message: String,
        tint: Color? = nil
    ) -> some View {
        modifier(
            ToastPresenter(
                isPresented: isPresented,
                duration: duration,
                systemImageName: systemImageName,
                message: message,
                tint: tint
            )
        )
    }
}

struct VisualEffectView: UIViewRepresentable {
    var style: UIBlurEffect.Style

    func makeUIView(context: Context) -> UIVisualEffectView {
        UIVisualEffectView(effect: UIBlurEffect(style: style))
    }

    func updateUIView(_ uiView: UIVisualEffectView, context: Context) {
        uiView.effect = UIBlurEffect(style: style)
    }
}
