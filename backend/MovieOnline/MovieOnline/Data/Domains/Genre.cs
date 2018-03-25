using System.Collections.Generic;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Domains
{
    public class Genre : GenreEntity
    {
        public ICollection<GenreMovie> GenreMovies { get; set; }
    }
}