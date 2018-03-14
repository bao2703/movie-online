using System.Linq;
using Microsoft.EntityFrameworkCore;
using MovieOnline.Data.Domains;

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