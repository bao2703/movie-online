using System.ComponentModel.DataAnnotations;

namespace MovieOnline.Data.Entities
{
    public class GenreEntity
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        public string Description { get; set; }
    }
}