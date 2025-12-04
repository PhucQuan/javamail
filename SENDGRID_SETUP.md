# SendGrid Setup for Render Deployment

## Why SendGrid?
Render blocks SMTP ports (25, 587, 465) on free tier. SendGrid uses HTTPS API instead, which works perfectly on Render.

## Step 1: Create SendGrid Account

1. Go to: https://signup.sendgrid.com/
2. Sign up (free tier: 100 emails/day)
3. Verify your email address

## Step 2: Create API Key

1. Login to SendGrid Dashboard
2. Go to: Settings → API Keys
3. Click "Create API Key"
4. Name: "Render Java App"
5. Permissions: "Full Access" (or "Mail Send" only)
6. Click "Create & View"
7. **COPY THE API KEY** (you won't see it again!)
   - Format: `SG.xxxxxxxxxxxxxxxxxx.yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy`

## Step 3: Verify Sender Email

SendGrid requires sender verification:

1. Go to: Settings → Sender Authentication
2. Click "Verify a Single Sender"
3. Fill in your details:
   - From Name: "Email List Team"
   - From Email: quan610ll@gmail.com
   - Reply To: quan610ll@gmail.com
4. Check your email and click verification link

## Step 4: Configure Render

1. Go to Render Dashboard
2. Open your Web Service
3. Go to "Environment" tab
4. Add Environment Variable:
   - **Key:** `SENDGRID_API_KEY`
   - **Value:** `SG.xxxxxxxxxx...` (paste your API key)
5. Click "Save Changes"
6. Render will auto-redeploy

## Step 5: Update Code (Already Done!)

The code automatically detects if running on Render:
- **Local:** Uses Gmail SMTP
- **Render:** Uses SendGrid API

## Testing

### Local Test (Gmail SMTP):
```bash
mvn clean package
# Deploy to Tomcat
# Visit http://localhost:8080/
```

### Render Test (SendGrid API):
```bash
git add .
git commit -m "Add SendGrid support for Render"
git push origin main
# Render auto-deploys
# Visit https://your-app.onrender.com/
```

## Troubleshooting

### Error: "The from email does not contain a valid address"
- Make sure you verified the sender email in SendGrid
- Use the exact email you verified

### Error: "Unauthorized"
- Check SENDGRID_API_KEY is set correctly in Render
- Make sure API key has "Mail Send" permission

### Email not received
- Check SendGrid Dashboard → Activity Feed
- Look for delivery status
- Check spam folder

## Free Tier Limits

- **SendGrid Free:** 100 emails/day
- **Render Free:** Unlimited (but app spins down after 15 min)

## Alternative: Mailgun

If SendGrid doesn't work, try Mailgun:
- Free: 5,000 emails/month
- Similar API setup
- Also works on Render

## Code Structure

```
src/main/java/murach/util/
├── MailUtil.java           (Auto-detects environment)
├── MailUtilGmail.java      (For local development)
└── MailUtilSendGrid.java   (For Render deployment)
```

## Security Note

**NEVER commit API keys to Git!**
- Always use environment variables
- API keys are set in Render dashboard only
