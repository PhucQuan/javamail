# Deploy to Railway

## Why Railway?
- ✅ **SMTP ports NOT blocked** - Gmail SMTP works directly!
- ✅ Free tier: $5 credit/month (enough for small apps)
- ✅ Supports Docker
- ✅ Auto-deploy from GitHub
- ✅ PostgreSQL database included

## Step 1: Create Railway Account

1. Go to: https://railway.app/
2. Sign up with GitHub
3. Verify email

## Step 2: Create New Project

1. Click "New Project"
2. Select "Deploy from GitHub repo"
3. Choose: `PhucQuan/javamail`
4. Railway will auto-detect Dockerfile

## Step 3: Configure Environment (Optional)

Railway doesn't block SMTP, so Gmail works directly!

**Option A: Use Gmail SMTP (No setup needed!)**
- Code already configured
- Works out of the box
- No environment variables needed

**Option B: Use SendGrid (If you prefer)**
1. Go to project settings
2. Add environment variable:
   - Key: `SENDGRID_API_KEY`
   - Value: `SG.xxxxxxxxxx...`

## Step 4: Deploy

1. Railway auto-deploys on push
2. Wait 5-10 minutes for build
3. Get your URL: `https://your-app.up.railway.app`

## Step 5: Test

Visit your app:
```
https://your-app.up.railway.app/
```

Enter email and test - email will be sent via Gmail SMTP!

## Database Setup (If needed)

1. Click "New" → "Database" → "PostgreSQL"
2. Railway auto-creates database
3. Get connection string from Variables tab
4. Update `context-render.xml` with Railway database URL

## Troubleshooting

### Build fails
- Check Railway logs
- Make sure Dockerfile exists
- Verify Java 11 is used

### Email not sending
- Check Railway logs for errors
- Gmail SMTP should work (ports not blocked)
- If fails, add SendGrid API key

### App not accessible
- Check if deployment succeeded
- Look for "Deployed" status
- Click "View Logs" for details

## Cost

**Free Tier:**
- $5 credit/month
- ~500 hours runtime
- Perfect for testing/learning

**After free tier:**
- Pay as you go
- ~$0.01/hour
- Can pause when not using

## Advantages over Render

| Feature | Railway | Render |
|---------|---------|--------|
| SMTP Ports | ✅ Open | ❌ Blocked |
| Free Tier | $5/month | 750 hours |
| Deploy Speed | Fast | Slower |
| Database | Included | Separate |

## Commands

### View logs:
```bash
railway logs
```

### Redeploy:
```bash
git push origin main
# Railway auto-deploys
```

### Local test:
```bash
railway run mvn jetty:run
```

## Next Steps

1. Deploy to Railway
2. Test email functionality
3. Share your app URL!

Your app will be live at: `https://[your-app].up.railway.app/`
