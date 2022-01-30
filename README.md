
# auth0.post.login
auth0.post.login

    exports.onExecutePostLogin = async (event, api) => {
      if (event.user.user_metadata.pet_name) {
        return;
      }
    
      const token = api.redirect.encodeToken({
        secret: event.secrets.SIX_POST_LOGIN_SECRET,
        payload: {
          email: event.user.email
        },
      });
    
      api.redirect.sendUserTo("http://windows.nameid.io:8082/add-user-name", { 
        query: { session_token: token }
      });
    };
    
    exports.onContinuePostLogin = async (event, api) => {
    
      const payload = api.redirect.validateToken({
        secret: event.secrets.SIX_POST_LOGIN_SECRET
      });
    
      api.idToken.setCustomClaim('https://xxx.xxx.com/full_name',payload.full_name);
      api.user.setUserMetadata("full_name", payload.full_name);
    };
