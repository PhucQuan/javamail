# Email Setup Guide for Exercise 14-1

## Gmail Setup (Recommended)

### Step 1: Enable 2-Step Verification
1. Go to your Google Account: https://myaccount.google.com/
2. Click "Security" in the left menu
3. Under "Signing in to Google", click "2-Step Verification"
4. Follow the steps to enable it

### Step 2: Create App Password
1. After enabling 2-Step Verification, go back to Security
2. Under "Signing in to Google", click "App passwords"
3. Select app: "Mail"
4. Select device: "Other (Custom name)" → Enter "Java Email App"
5. Click "Generate"
6. Copy the 16-character password (remove spaces)

### Step 3: Update MailUtilGmail.java
Open `src/main/java/murach/util/MailUtilGmail.java` and replace:

```java
transport.connect("your-email@gmail.com", "your-app-password");
```

With your actual Gmail and App Password:

```java
transport.connect("yourname@gmail.com", "abcd efgh ijkl mnop");
```

### Step 4: Update EmailListServlet.java
Open `src/main/java/murach/email/EmailListServlet.java` and replace:

```java
String from = "your-email@gmail.com";
```

With your actual Gmail:

```java
String from = "yourname@gmail.com";
```

## Testing

1. Build and deploy the application
2. Go to http://localhost:8080/
3. Enter your name and email address
4. Click "Join Now"
5. Check your email inbox for the confirmation message

## Troubleshooting

### Error: Authentication failed
- Make sure you're using App Password, not your regular Gmail password
- Check that 2-Step Verification is enabled
- Verify the email and password are correct

### Error: Connection timeout
- Check your internet connection
- Make sure port 587 is not blocked by firewall
- Try using port 465 with SSL instead

### Email not received
- Check spam/junk folder
- Wait a few minutes (email delivery can be delayed)
- Check Tomcat logs for error messages

## Alternative: Using Local SMTP Server (for testing)

If you don't want to use Gmail, you can use a local SMTP server like:
- **FakeSMTP**: https://github.com/Nilhcem/FakeSMTP
- **MailHog**: https://github.com/mailhog/MailHog
- **Papercut**: https://github.com/ChangemakerStudios/Papercut-SMTP

These tools capture emails locally without actually sending them.
