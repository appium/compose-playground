# compose-playground

[![Build](https://github.com/appium/compose-playground/actions/workflows/build.yml/badge.svg)](https://github.com/appium/compose-playground/actions/workflows/build.yml)
[![Test](https://github.com/appium/compose-playground/actions/workflows/test-push.yml/badge.svg)](https://github.com/appium/compose-playground/actions/workflows/test-push.yml)

Fixture Android app for end-to-end validation of [Appium Espresso driver](https://github.com/appium/appium-espresso-driver) Jetpack Compose support. The home screen uses classic Android views (Espresso `driver: 'espresso'`); demo screens use Compose (`driver: 'compose'`).

See the [Jetpack Compose support](https://github.com/appium/appium-espresso-driver#jetpack-compose-support) section in espresso-driver for usage.

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

| Menu label (classic UI) | Compose semantics | Used by espresso-driver |
|-------------------------|-------------------|-------------------------|
| Clickable Component | `testTag("lol")`, `contentDescription = "desc"`, text **Click to see dialog**; dialog **Congratulations! You just clicked the text successfully** | jetpack-compose-e2e-specs, jetpack-compose-attributes-e2e-specs |
| Horizontal Carousel | Two nodes with text **Grace Hopper** | jetpack-compose-e2e-specs |
| Display Text | Non-empty Compose tree (page source) | jetpack-compose-source-e2e-specs |
| Text Input Components | `testTag("text_input")`, initial text **Enter your text here** | jetpack-componse-element-values-e2e-specs |

Each Compose screen sets `testTagsAsResourceId = true` on the root so Appium can resolve `testTag` / `view-tag` / `tag name` locators.

## CI

- **Build** — assembles debug and release APKs on pull requests.
- **Test** — runs `connectedDebugAndroidTest` on an API 29 x86_64 emulator (PR via build workflow; `master` via test-push workflow).
- **Lint** — ESLint on Node tooling files.
- **Release** — semantic-release on `master` publishes `ComposePlayground-debug.apk` and `ComposePlayground-release.apk`.

## Releasing

Releases are automated with [semantic-release](https://github.com/semantic-release/semantic-release) on pushes to `master`. Use [Conventional Commits](https://www.conventionalcommits.org/) (e.g. `feat:`, `fix:`). Version bumps sync `package.json` and `app/build.gradle.kts` (`versionName` / `versionCode`).

## Compatibility

- **minSdk** 26 (matches espresso-driver functional test matrix minimum)
- **targetSdk** 34, **compileSdk** 35
- Compose BOM is pinned in `gradle/libs.versions.toml`; coordinate major Compose bumps with [appium-espresso-driver](https://github.com/appium/appium-espresso-driver) `espresso-server` dependencies.

## License

Apache-2.0 — see [LICENSE](LICENSE).
