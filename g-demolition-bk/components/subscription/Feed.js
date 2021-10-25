const Feed = ({feed}) => {
  return <div>
    <a href="{feed.enclosure}" target="_blank">{feed.title}</a>
  </div>;
};

export default Feed;
