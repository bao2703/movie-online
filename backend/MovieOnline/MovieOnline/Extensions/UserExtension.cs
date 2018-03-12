using Microsoft.EntityFrameworkCore;
using MovieOnline.Data.Domains;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MovieOnline.Extensions
{
    public static class UserExtension
    {
        public static bool IsExistEmail(this DbSet<User> users, string email)
        {
            return users.Any(x => x.Email == email);
        }
    }
}
