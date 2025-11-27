# Deployment Configuration Guide

## 📁 File Structure

```
SQLGatewayApp/
├── Dockerfile              # For local Docker deployment
├── Dockerfile.render       # For Render cloud deployment
├── docker-compose.yml      # For local multi-container setup
├── context-docker.xml      # DB config for local Docker (postgres:5432)
├── context-render.xml      # DB config for Render cloud (YOUR_HOST:5432)
└── src/main/webapp/META-INF/
    └── context.xml         # DB config for NetBeans local (localhost:5432)
```

## 🎯 Which Files to Use

| Environment | Dockerfile | Context File | Database Host |
|-------------|------------|--------------|---------------|
| **NetBeans Local** | - | `src/main/webapp/META-INF/context.xml` | `localhost:5432` |
| **Docker Local** | `Dockerfile` | `context-docker.xml` | `postgres:5432` |
| **Render Cloud** | `Dockerfile.render` | `context-render.xml` | Render hostname |

## 🚀 Deployment Instructions

### For Render Cloud

1. **Get Database Credentials:**
   - Go to Render Dashboard → Your PostgreSQL Database
   - Copy: Hostname, Port, Database, Username, Password

2. **Update `context-render.xml`:**
   ```xml
   url="jdbc:postgresql://YOUR_RENDER_HOST:5432/murach"
   username="YOUR_USERNAME" 
   password="YOUR_PASSWORD"
   ```

3. **Configure Render Service:**
   - In Render Dashboard → Your Web Service → Settings
   - **Docker Command:** Leave empty (uses default CMD)
   - **Dockerfile Path:** `Dockerfile.render`
   - Click "Save Changes"

4. **Deploy:**
   - Push code to GitHub
   - Render will auto-deploy using `Dockerfile.render`

### For Local Docker

```bash
# Uses Dockerfile and context-docker.xml
docker-compose up -d
```

### For NetBeans Local

```bash
# Uses src/main/webapp/META-INF/context.xml
# Just click Run in NetBeans
```

## 🔧 Troubleshooting

### "Connection to localhost:5432 refused" on Render
**Problem:** Using wrong context file  
**Solution:** Ensure Render uses `Dockerfile.render` which loads `context-render.xml`

### "Connection to postgres:5432 refused" locally
**Problem:** Using Docker context file in NetBeans  
**Solution:** NetBeans should use `src/main/webapp/META-INF/context.xml`

### Database credentials not working
**Problem:** Wrong credentials in context file  
**Solution:** Double-check credentials from Render Dashboard

## 📝 Important Notes

- **Never commit passwords to Git!** Use environment variables for production
- Keep `context-render.xml` in `.gitignore` if it contains real passwords
- Each environment needs its own database configuration
- Render automatically detects Dockerfile, specify `Dockerfile.render` in settings
