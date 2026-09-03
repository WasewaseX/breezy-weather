# هوادار — راهنمای ساخت نهایی روی گیت‌هاب (Havadar build guide)

## وضعیت فعلی (آمادهٔ پوش)
- ریپوی محلی: `C:\Users\SAMIR\.openclaw-autoclaw\workspace\havadar-weather`
- شاخه: `havadar/fa-main` (کامیت `d26ccf2`)
- برند: «هوادار» (نام لانچر، نام Tile، نام انگلیسی Havadar)
- فارسی پیش‌فرض: ۷۸۰ رشته از ۱۱۶۲ (بقیه فعلاً انگلیسی — عمدتاً تنظیمات پیشرفته)
- گردش‌کار CI: `.github/workflows/havadar-build.yml` → بیلد `assembleBasicDebug` (بدون کانفیگ breezy = مجاز برای فورک) و آپلود artifact به نام `havadar-debug-apk`

## مسیر ۱ (پیشنهادی): توکن بده، بقیه‌اش با من
1. برو به `github.com/settings/tokens/new`
2. Note = `havadar`، Expiration = 30 روز، تیک‌ها: **repo** و **workflow**
3. توکن را در همین چت بفرست — من فورک می‌سازم، پوش می‌دهم، بیلد CI را دنبال می‌کنم و APK نهایی را تحویل می‌دهم.

## مسیر ۲ (دستی، بدون توکن)
1. در مرورگر: `github.com/breezy-weather/breezy-weather` → دکمهٔ **Fork** (به اکانت WasewaseX).
2. در PowerShell، داخل پوشهٔ `havadar-weather`:
   ```
   git remote set-url origin https://github.com/WasewaseX/breezy-weather.git
   git push -u origin havadar/fa-main
   ```
   پنجرهٔ ورود گیت‌هاب باز می‌شود؛ لاگین کن (توکن قدیمی ذخیره‌شده منقضی شده است).
3. در گیت‌هاب: تب **Actions** → ورک‌فلو **Havadar build** → صبر کن سبز شود → دانلود artifact **havadar-debug-apk**.

## نصب و تست
- فایل APK داخل artifact را نصب کن (بستهٔ دیباگ: `org.breezyweather.debug` — کنار اپ‌های دیگر نصب می‌شود).
- چک‌لیست: اسم لانچر «هوادار» / رابط فارسی / منبع Open-Meteo بدون کلید کار می‌کند / ویجت‌ها فارسی.
- نسخهٔ پایه برای مقایسه: `havadar-base-test_breezy-v6.2.2_standard.apk` (نسخهٔ رسمی upstream).

## محدودیت‌های صادقانهٔ نسخهٔ فعلی
- ۳۸۳ رشتهٔ انگلیسی باقی مانده (تنظیمات پیشرفته، کلیدهای API، نام بعضی منابع).
- آیکون هنوز آیکون Breezy است (قدم بعد: آیکون اختصاصی هوادار).
- package هنوز `org.breezyweather(.debug)` — قبل از انتشار در بازار باید به بستهٔ اختصاصی هوادار تغییر کند.
- APK فعلی دیباگ است (امضای دیباگ، قابل نصب ولی برای انتشار نهایی نه)؛ بیلد release امضادار نیاز به keystore خودت دارد.
- مجوز: Apache-2.0. انتشار APK تغییر یافته **با کانفیگ breezy ممنوع** است؛ بیلد ما عمداً بدون آن است ✔ (attribution پروژهٔ upstream در About حفظ شده).
