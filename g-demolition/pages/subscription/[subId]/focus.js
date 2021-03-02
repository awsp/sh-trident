import {useRouter} from "next/router";

const Focus = () => {
  const router = useRouter();
  const {subId} = router.query;

  return <>Sub ID: {subId}</>
};

export default Focus;