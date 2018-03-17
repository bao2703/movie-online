using System.Collections.Generic;

namespace MovieOnline.Data.Domains
{
    public class Episode
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Source { get; set; }

        public int MovieId { get; set; }

        public Movie Movie { get; set; }

        public ICollection<Comment> Comments { get; set; }
    }
}