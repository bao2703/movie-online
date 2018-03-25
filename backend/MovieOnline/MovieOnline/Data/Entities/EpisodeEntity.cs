using System.Collections.Generic;
using MovieOnline.Data.Bases;

namespace MovieOnline.Data.Entities
{
    public class EpisodeEntity : Episode
    {
        public MovieEntity Movie { get; set; }

        public ICollection<CommentEntity> Comments { get; set; }
    }
}