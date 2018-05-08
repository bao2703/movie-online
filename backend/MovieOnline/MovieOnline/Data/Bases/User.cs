using System.ComponentModel.DataAnnotations;

namespace MovieOnline.Data.Bases
{
    public class User
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string AvatarUrl { get; set; }

        [EmailAddress]
        public string Email { get; set; }

        public string Password { get; set; }

        public Role Role { get; set; }
    }
}