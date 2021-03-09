import {Tag} from "@blueprintjs/core";

const FeedList = ({feeds}) => {
  return <>
    {feeds.sort((a, b) => new Date(a.pubDate) - new Date(b.pubDate)).map(feed => (
      <p key={`feed-${feed.id}`}>
        <Tag intent="primary">{feed.pubDate}</Tag>&nbsp;
        <a href={feed.enclosure} target="_blank">{feed.title}</a>
      </p>
    ))}
  </>
};

export default FeedList;