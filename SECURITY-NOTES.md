# Havadar signing security notes

## Current state (PRE-PRODUCTION)
keystore/havadar-release.jks is committed to this public repository with the
password HavadarSign2026. This is a deliberate trade-off to enable fully
automated signed releases before the app is publicly distributed.

## What this means
- Anyone with the repo can build and sign an APK that installs as an update
  over your releases (same signature).
- Acceptable ONLY while the app is in closed testing and users install from
  links you control.

## Rotation before public/store launch (REQUIRED)
1. Generate a new keystore locally: keytool -genkeypair -v -keystore havadar-release.jks -alias havadar -keyalg RSA -keysize 2048 -validity 10000
2. Remove it from the repo and add a .gitignore entry.
3. In the GitHub repo web UI: Settings > Secrets and variables > Actions > add HAVADAR_STORE_FILE_B64 (base64 of the keystore), HAVADAR_STORE_PASS, HAVADAR_KEY_ALIAS, HAVADAR_KEY_PASS.
4. Update .github/workflows/havadar-release.yml to decode the secret and sign with it; bump versionCode and publish.
5. Users must uninstall/reinstall once (new signature) - do this BEFORE you have real users.
