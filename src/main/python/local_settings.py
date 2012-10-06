'''
Configuration Settings
'''

TWILIO_ACCOUNT_SID = "AC3a0cdf5a38878ed22525986d866fbd55"
TWILIO_AUTH_TOKEN = "7ff94f7585adac2b79ca75d2c6dc816f"
TWILIO_APP_SID = "APc4937f02b0af4a68a6f86669ac98ad14"
TWILIO_CALLER_ID = "+13212855294"

'''
# Begin Heroku configuration - configured through environment variables.
import os
TWILIO_ACCOUNT_SID = os.environ.get('TWILIO_ACCOUNT_SID', None)
TWILIO_AUTH_TOKEN = os.environ.get('TWILIO_AUTH_TOKEN', None)
TWILIO_CALLER_ID = os.environ.get('TWILIO_CALLER_ID', None)
TWILIO_APP_SID = os.environ.get('TWILIO_APP_SID', None)
'''
