# Email Services Comparison

## Overview

Code hiện tại hỗ trợ 5 email services. Chọn 1 trong số này:

| Service | Free Tier | Setup | Khuyên dùng |
|---------|-----------|-------|-------------|
| **Gmail SMTP** | Unlimited | Cần App Password | ✅ Local dev |
| **Mailgun** | 5,000/month | Dễ | ✅✅ Best choice |
| **Resend** | 3,000/month | Rất dễ | ✅✅ Modern |
| **SendGrid** | 100/day | Trung bình | ✅ Popular |
| **Brevo** | 300/day | Dễ | ✅ Good |

## 1. Gmail SMTP (Local Development)

**Pros:**
- ✅ Unlimited emails
- ✅ No signup needed
- ✅ Works everywhere

**Cons:**
- ❌ Cần App Password
- ❌ Bị chặn trên Render free tier
- ❌ Không professional

**Setup:**
1. Bật 2-Step Verification
2. Tạo App Password
3. Cập nhật code

**Use case:** Local development only

---

## 2. Mailgun (KHUYÊN DÙNG NHẤT!)

**Pros:**
- ✅ 5,000 emails/month FREE
- ✅ Dễ setup
- ✅ Professional
- ✅ Reliable
- ✅ Good documentation

**Cons:**
- ❌ Cần verify credit card (không charge)

**Setup:**
1. Sign up: https://signup.mailgun.com/
2. Verify email
3. Get API key & domain
4. Add env variables:
   - `MAILGUN_API_KEY`
   - `MAILGUN_DOMAIN`

**Free tier:** 5,000 emails/month

---

## 3. Resend (MỚI - RẤT TỐT!)

**Pros:**
- ✅ 3,000 emails/month FREE
- ✅ Rất dễ setup
- ✅ Modern API
- ✅ No credit card needed
- ✅ Beautiful dashboard

**Cons:**
- ❌ Mới (2023) - ít người dùng

**Setup:**
1. Sign up: https://resend.com/signup
2. Verify email
3. Get API key
4. Add env variable: `RESEND_API_KEY`

**Free tier:** 3,000 emails/month

---

## 4. SendGrid

**Pros:**
- ✅ Popular
- ✅ Trusted by many companies
- ✅ Good documentation

**Cons:**
- ❌ Chỉ 100 emails/day
- ❌ Setup phức tạp hơn
- ❌ Cần verify sender

**Setup:**
1. Sign up: https://signup.sendgrid.com/
2. Verify email
3. Create API key
4. Verify sender email
5. Add env variable: `SENDGRID_API_KEY`

**Free tier:** 100 emails/day (3,000/month)

---

## 5. Brevo (Sendinblue)

**Pros:**
- ✅ 300 emails/day FREE
- ✅ Dễ setup
- ✅ Marketing features

**Cons:**
- ❌ Giới hạn 300/day
- ❌ Ít phổ biến hơn

**Setup:**
1. Sign up: https://www.brevo.com/
2. Verify email
3. Get API key
4. Add env variable: `BREVO_API_KEY`

**Free tier:** 300 emails/day (9,000/month)

---

## Recommendation

### For Learning/Testing:
**→ Resend** hoặc **Mailgun**
- Dễ setup nhất
- Không cần credit card (Resend)
- Đủ quota cho học tập

### For Production:
**→ Mailgun** hoặc **SendGrid**
- Professional
- Reliable
- Scalable

### For Local Development:
**→ Gmail SMTP**
- Không cần signup
- Unlimited

---

## How to Use

Code tự động detect service dựa trên environment variable:

```bash
# Local (Gmail SMTP)
# No env variable needed

# Mailgun
MAILGUN_API_KEY=key-xxxxx
MAILGUN_DOMAIN=sandboxXXX.mailgun.org

# Resend
RESEND_API_KEY=re_xxxxx

# SendGrid
SENDGRID_API_KEY=SG.xxxxx

# Brevo
BREVO_API_KEY=xkeysib-xxxxx
```

Chỉ cần set 1 trong các env variable trên, code sẽ tự động dùng service đó!

---

## Quick Start Guide

### Option 1: Resend (Easiest)
```bash
1. Go to https://resend.com/signup
2. Sign up with email
3. Get API key
4. Deploy to Railway/Render
5. Add env: RESEND_API_KEY=re_xxxxx
```

### Option 2: Mailgun (Best)
```bash
1. Go to https://signup.mailgun.com/
2. Sign up
3. Get API key & domain
4. Deploy to Railway/Render
5. Add env:
   MAILGUN_API_KEY=key-xxxxx
   MAILGUN_DOMAIN=sandboxXXX.mailgun.org
```

### Option 3: SendGrid (Popular)
```bash
1. Go to https://signup.sendgrid.com/
2. Sign up
3. Create API key
4. Verify sender email
5. Deploy to Railway/Render
6. Add env: SENDGRID_API_KEY=SG.xxxxx
```

---

## Testing

All services work the same way in your app:
1. User enters email
2. Click "Join Now"
3. Email sent via chosen service
4. User receives welcome email

No code changes needed - just set environment variable!
