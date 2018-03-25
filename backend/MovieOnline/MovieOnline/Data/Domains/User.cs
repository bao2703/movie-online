using System.Collections.Generic;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Domains
{
    public class User : UserEntity
    {
        public ICollection<Like> Likes { get; set; }
    }
}