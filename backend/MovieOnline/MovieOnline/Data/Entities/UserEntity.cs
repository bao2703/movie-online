using System.Collections.Generic;
using MovieOnline.Data.Bases;

namespace MovieOnline.Data.Entities
{
    public class UserEntity : User
    {
        public ICollection<LikeEntity> Likes { get; set; }
    }
}