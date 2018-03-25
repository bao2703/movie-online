using System.ComponentModel.DataAnnotations;

namespace MovieOnline.Data.Bases
{
    public class User
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Avatar { get; set; }

        [Required]
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        public string Password { get; set; }

        public Role Role { get; set; }
    }
}