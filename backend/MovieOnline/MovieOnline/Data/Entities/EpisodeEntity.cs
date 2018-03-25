namespace MovieOnline.Data.Entities
{
    public class EpisodeEntity
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Source { get; set; }

        public int MovieId { get; set; }
    }
}