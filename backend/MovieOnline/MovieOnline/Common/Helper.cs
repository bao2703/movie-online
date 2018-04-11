using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using MovieOnline.Data.Bases;

namespace MovieOnline.Common
{
    public class Helper
    {
        private static readonly JwtSecurityTokenHandler JwtSecurityTokenHandler = new JwtSecurityTokenHandler();

        public static string GenerateJwt(User user)
        {
            var claims = new[]
            {
                new Claim("id", user.Id.ToString()),
                new Claim("name", user.Name),
                new Claim("avatarUrl", string.IsNullOrEmpty(user.AvatarUrl) ? "null" : user.AvatarUrl),
                new Claim("email", user.Email),
                new Claim("role", user.Role.ToString())
            };
        
            var token = new JwtSecurityToken(
                JwtConfig.Issuer,
                JwtConfig.Audience,
                claims,
                JwtConfig.NotBefore,
                JwtConfig.Expires,
                JwtConfig.SigningCredentials);

            var encodedToken = JwtSecurityTokenHandler.WriteToken(token);

            return encodedToken;
        }
    }
}
