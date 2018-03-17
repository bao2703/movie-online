using System.Collections.Generic;

namespace MovieOnline.Data.Domains
{
    public class Genre
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Description { get; set; }

        public ICollection<GenreMovie> GenreMovies { get; set; }
    }
}