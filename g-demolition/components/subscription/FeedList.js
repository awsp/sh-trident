import {formatDate} from "../../helpers/feed-helper";

const FeedList = ({feeds}) => {
  return <table>
    <thead>
      <tr>
        <th>Publish Date</th>
        <th>Content</th>
      </tr>
    </thead>
    <tbody>
    {feeds.sort((a, b) => new Date(b.pubDate) - new Date(a.pubDate)).map(feed => (
      <tr key={`feed-${feed.id}`}>
        <td className="no-wrap">{formatDate(feed.pubDate)}</td>
        <td className="no-wrap"><a href={feed.enclosure} target="_blank">{feed.title}</a></td>
      </tr>
    ))}
    </tbody>
  </table>
};

export default FeedList;