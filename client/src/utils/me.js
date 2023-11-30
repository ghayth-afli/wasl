import axios from "axios";
import { hosts } from "../const.js";

export const me = async (token) => {
  console.log("token", token);
  const res = await axios.get(`${hosts.backend}/api/myprofile`, {
    headers: {
      "Content-Type": "Application/json ; charset=UTF-8",
      Authorization: `Bearer ${token}`,
    },
  });

  if (res.status === 200) {
    return res.data;
  } else {
    return null;
  }
};
