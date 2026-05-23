# compose-playground

[![Build](https://github.com/appium/compose-playground/actions/workflows/build.yml/badge.svg)](https://github.com/appium/compose-playground/actions/workflows/build.yml)

A small Android fixture app for exploring and validating **Jetpack Compose** UI behavior alongside classic Android views. The home screen uses traditional `TextView` menu items; each demo screen is built with Compose.

Use it for manual exploration, instrumented Compose UI tests, or as a stable APK in your own automation pipelines.

## Download

Install the latest debug APK from [GitHub Releases](https://github.com/appium/compose-playground/releases/latest).

Versioned URL pattern:

```text
https://github.com/appium/compose-playground/releases/download/vX.Y.Z/ComposePlayground-debug.apk
```

## Requirements

- JDK 17
- Android SDK Platform 35 (compile SDK)
- Node.js 20+ and npm 10+ (release tooling and ESLint)

## Build locally

```bash
npm install
npm run build:debug
```

Debug APK: `app/build/outputs/apk/debug/app-debug.apk`

Release (unsigned): `npm run build:release` → `app/build/outputs/apk/release/app-release-unsigned.apk`

### Instrumented tests

With an API 26+ emulator or device connected:

```bash
./gradlew :app:connectedDebugAndroidTest
```

## Demo catalog

| Menu label (classic UI) | Compose semantics | What to try |
|-------------------------|-------------------|-------------|
| Clickable Component | `testTag("lol")`, `contentDescription = "desc"`, text **Click to see dialog**; dialog **Congratulations! You just clicked the text successfully** | Tap by tag, content description, or text; confirm dialog |
| Horizontal Carousel | Two nodes with text **Grace Hopper** | Find duplicate visible text in a horizontal list |
| Display Text | Non-empty Compose tree | Inspect hierarchy / page source |
| Text Input Components | `testTag("text_input")`, initial text **Enter your text here** | Append, replace, and clear field text |

Each Compose screen sets `testTagsAsResourceId = true` on the root so `testTag` values are exposed as view resource IDs ([Compose testing interoperability](https://developer.android.com/develop/ui/compose/testing/interoperability)).

## CI

- **Build** — assembles debug and release APKs on pull requests.
- **Test** — runs `connectedDebugAndroidTest` on an emulator on pull requests only.
- **Lint** — ESLint on Node tooling files.
- **Release** — semantic-release on `main` publishes `ComposePlayground-debug.apk` and `ComposePlayground-release.apk`.

## Releasing

Releases are automated with [semantic-release](https://github.com/semantic-release/semantic-release) on pushes to `main`. Use [Conventional Commits](https://www.conventionalcommits.org/) (e.g. `feat:`, `fix:`). Version bumps sync `package.json` and `app/build.gradle.kts` (`versionName` / `versionCode`).

## Compatibility

- **minSdk** 26
- **targetSdk** 34, **compileSdk** 35
- Compose BOM is pinned in `gradle/libs.versions.toml`; bump it when you need newer Compose APIs.

## License

Apache-2.0 — see [LICENSE](LICENSE).
