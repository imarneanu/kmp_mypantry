import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinIos.shared.start()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
