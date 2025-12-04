# FakeSMTP Setup - Local Email Testing

## What is FakeSMTP?
FakeSMTP is a fake SMTP server that captures emails locally without actually sending them. Perfect for testing!

## Download & Setup

### Option 1: Download JAR file
1. Download from: https://github.com/Nilhcem/FakeSMTP/releases
2. Download `fakeSMTP-latest.zip`
3. Extract and run `fakeSMTP-2.0.jar`

### Option 2: Direct download
```bash
# Download directly
curl -L https://github.com/Nilhcem/FakeSMTP/releases/download/v2.0/fakeSMTP-2.0.jar -o fakeSMTP.jar
```

## Running FakeSMTP

### Windows:
```cmd
java -jar fakeSMTP-2.0.jar
```

### Configuration:
1. Click "Start server" button
2. Default port: 25 (or change to 2525 if port 25 is blocked)
3. Choose output directory to save emails
4. Leave it running while testing your app

## Testing Your Application

1. **Start FakeSMTP** (keep it running)
2. **Start Tomcat** with your application
3. **Go to** http://localhost:8080/
4. **Enter** name and email
5. **Click** "Join Now"
6. **Check FakeSMTP window** - you'll see the email captured!
7. **Open saved .eml file** to view email content

## If Port 25 is Blocked

If you get "Address already in use" error, use port 2525:

### Update MailUtilGmail.java:
```java
props.put("mail.smtp.host", "localhost");
props.put("mail.smtp.port", "2525");  // Add this line
```

Then start FakeSMTP on port 2525.

## Alternative: MailHog (More features)

If you want a web interface:

1. Download: https://github.com/mailhog/MailHog/releases
2. Run: `MailHog.exe`
3. Web UI: http://localhost:8025
4. SMTP: localhost:1025

Update code to use port 1025 if using MailHog.

## Troubleshooting

### Email not captured
- Make sure FakeSMTP is running
- Check port number matches (default 25)
- Look at Tomcat logs for errors

### Connection refused
- FakeSMTP must be started BEFORE testing
- Check firewall settings
- Try different port (2525)

### No .eml files saved
- Make sure you selected output directory in FakeSMTP
- Check FakeSMTP console for messages
