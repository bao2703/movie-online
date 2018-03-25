using System.Collections.Generic;
using MovieOnline.Data.Bases;

namespace MovieOnline.Data.Entities
{
    public class GenreEntity : Genre
    {
        public ICollection<GenreMovieEntity> GenreMovies { get; set; }
    }
}