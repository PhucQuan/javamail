# Exercise 14-1: Send an Email from a Servlet

## ✅ Completed Tasks

### 1. Added JavaMail API
- Added `jakarta.mail` dependency (version 2.0.1) to `pom.xml`
- Compatible with Jakarta EE and Tomcat 11

### 2. Created Email Helper Class
- Created `MailUtilGmail.java` in `murach.util` package
- Configured for Gmail SMTP server (smtp.gmail.com:587)
- Uses TLS encryption for secure email sending
- Supports both plain text and HTML email bodies

### 3. Modified EmailListServlet
- Added email sending functionality after successful user registration
- Sends welcome email to new subscribers
- Includes error handling (continues even if email fails)
- Email content includes personalized greeting with user's first name

### 4. Email Configuration Required

**IMPORTANT:** Before testing, you must configure your Gmail credentials:

1. **In `MailUtilGmail.java` (line 42):**
   ```java
   transport.connect("your-email@gmail.com", "your-app-password");
   ```
   Replace with your actual Gmail and App Password

2. **In `EmailListServlet.java` (line 52):**
   ```java
   String from = "your-email@gmail.com";
   ```
   Replace with your actual Gmail

## 📧 Email Content

When a user joins the email list, they receive:

**Subject:** Welcome to our email list

**Body:**
```
Dear [FirstName],

Thank you for joining our email list. We'll use this email address to send you announcements about new products and promotions.

Have a great day!

The Email List Team
```

## 🧪 Testing Instructions

### Before Testing:
1. Follow instructions in `EMAIL_SETUP.md` to get Gmail App Password
2. Update credentials in `MailUtilGmail.java` and `EmailListServlet.java`
3. Rebuild and redeploy the application

### Testing Steps:
1. Navigate to: `http://localhost:8080/`
2. Enter your name and email address
3. Click "Join Now"
4. Check your email inbox (may take 1-2 minutes)
5. If email not received, check:
   - Spam/Junk folder
   - Tomcat logs for error messages
   - Gmail credentials are correct

### Expected Results:
- ✅ User is added to database
- ✅ "Thanks" page is displayed
- ✅ Confirmation email is received
- ❌ If email fails, user still added but message shows "Email sent failed"

## 🔧 Troubleshooting

### Common Issues:

**1. Authentication Failed**
- Solution: Use App Password, not regular Gmail password
- Enable 2-Step Verification first

**2. Connection Timeout**
- Check firewall settings (port 587)
- Verify internet connection
- Try alternative port 465 with SSL

**3. Email Not Received**
- Check spam folder
- Wait a few minutes
- Verify "from" email is correct
- Check Tomcat logs for detailed errors

### Debug Mode:
The code includes `session.setDebug(true)` which prints detailed SMTP communication to Tomcat logs.

## 📝 Code Structure

```
src/main/java/murach/
├── email/
│   └── EmailListServlet.java (modified - sends email)
└── util/
    └── MailUtilGmail.java (new - email helper)
```

## 🔐 Security Notes

**NEVER commit credentials to Git!**

For production:
- Use environment variables for credentials
- Store in external configuration file
- Use secrets management service
- Consider using email service APIs (SendGrid, Mailgun, etc.)

## 📚 Additional Resources

- JavaMail API Documentation: https://jakarta.ee/specifications/mail/
- Gmail SMTP Settings: https://support.google.com/mail/answer/7126229
- App Passwords Guide: https://support.google.com/accounts/answer/185833
