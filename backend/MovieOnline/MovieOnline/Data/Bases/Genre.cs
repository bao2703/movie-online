using System.ComponentModel.DataAnnotations;

namespace MovieOnline.Data.Bases
{
    public class Genre
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Description { get; set; }
    }
}