using System.Collections.Generic;
using MovieOnline.Data.Bases;

namespace MovieOnline.Data.Entities
{
    public class MovieEntity : Movie
    {
        public ICollection<GenreMovieEntity> GenreMovies { get; set; }

        public ICollection<EpisodeEntity> Episodes { get; set; }

        public ICollection<LikeEntity> Likes { get; set; }

        public ICollection<CommentEntity> Comments { get; set; }
    }
}