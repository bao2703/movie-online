using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.IdentityModel.Tokens;

namespace MovieOnline.Common
{
    public class JwtConfig
    {
        public static readonly string Issuer = "http://localhost:5000";

        public static readonly string Audience = "http://localhost:3000";

        public static readonly string Secret = "super extremely very long long long secret";

        public static DateTime NotBefore => DateTime.Now;

        public static DateTime Expires => NotBefore.AddDays(1);

        public static readonly SymmetricSecurityKey SymmetricSecurityKey =
            new SymmetricSecurityKey(Encoding.UTF8.GetBytes(Secret));

        public static readonly SigningCredentials SigningCredentials =
            new SigningCredentials(SymmetricSecurityKey, SecurityAlgorithms.HmacSha256);

        public static readonly TokenValidationParameters TokenValidationParameters = new TokenValidationParameters
        {
            ValidIssuer = Issuer,
            ValidateIssuer = true,
            ValidAudience = Audience,
            ValidateAudience = true,
            IssuerSigningKey = SymmetricSecurityKey,
            ValidateIssuerSigningKey = true,
            ValidateLifetime = true
        };
    }
}
