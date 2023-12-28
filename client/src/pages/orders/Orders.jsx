import React, { useEffect, useState } from "react";
import { hosts } from "../../const.js";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import "./Orders.scss";
import { ChakraProvider } from "@chakra-ui/react";
import OrderState from "../../components/buttons/OrderState.jsx";
import Review from "../../components/buttons/Review.jsx";

const Orders = () => {
  const monthNames = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [requests, setRequests] = useState([]);
  const [apiEndpoint, setApiEndpoint] = useState(null);
  console.log("requests", requests);
  useEffect(() => {
    fetch(`${hosts.backend}/api/myprofile`, {
      method: "GET",
      headers: {
        "Content-Type": "Application/json ; charset=UTF-8",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        if (data.error == "Unauthorized") {
          throw new Error(data.error);
        }
        setUser(data);
      })
      .catch((err) => {
        if (err.message == "Unauthorized") {
          navigate("/login");
        } else {
          console.log(err);
        }
      });
  }, []);

  useEffect(() => {
    if (user) {
      setApiEndpoint(
        user?.traveler
          ? `${hosts.backend}/api/requests`
          : `${hosts.backend}/api/myrequests`
      );
    }
  }, [user]);

  useEffect(() => {
    if (!apiEndpoint) {
      console.log("no apiEndpoint");
      return;
    }
    fetch(`${apiEndpoint}`, {
      method: "GET",
      headers: {
        "Content-Type": "Application/json ; charset=UTF-8",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setRequests(data);
      });
  }, [apiEndpoint]);

  return (
    <div className="orders">
      <div className="container">
        <div className="title">
          <h1>Orders</h1>
        </div>
        <table>
          <tr>
            <th>Offer</th>
            <th>Title</th>
            <th>Price</th>
            <th>Status</th>
            <th>Requested Date</th>
            {<th>{user?.traveler ? "Sender" : "Traveler"}</th>}
            <th>Contact</th>
            <th>Order Action</th>
            <th>Review</th>
          </tr>
          {user &&
            user?.traveler &&
            requests &&
            requests.map((item) => {
              return item.requests.map((request) => {
                const gigLink = `/gig/${request.offer.id}`;
                const date = new Date(request.startRequest);
                const day = date.getDate();
                const month = date.getMonth() + 1;
                const year = date.getFullYear();
                return (
                  <tbody key={request.id}>
                    <tr>
                      <td>
                        {" "}
                        <Link to={gigLink}> Go to offer </Link>
                      </td>
                      <td>{request.offer.title}</td>
                      <td>{request.totalPrice}</td>
                      <td
                        className={
                          request.status === "PENDING" ? "pending" : "completed"
                        }
                      >
                        {request.status}
                      </td>
                      <td>
                        {day} {monthNames[month - 1]} {year}
                      </td>
                      <td>{request.sender.user?.username}</td>
                      <td>
                        <img
                          className="message"
                          src="./img/message.png"
                          alt=""
                        />
                      </td>
                      <td width={"75px"}>
                        {" "}
                        <ChakraProvider>
                          <OrderState
                            role="traveler"
                            offerId={request.offer.id}
                            requestId={request.id}
                          />
                        </ChakraProvider>
                      </td>
                    </tr>
                  </tbody>
                );
              });
            })}

          {user &&
            !user?.traveler &&
            requests &&
            requests.map((item) => {
              const request = item;
              const gigLink = `/gig/${request.offer.id}`;
              const date = new Date(request.startRequest);
              const day = date.getDate();
              const month = date.getMonth() + 1;
              const year = date.getFullYear();
              return (
                <tbody key={request.id}>
                  <tr>
                    <td>
                      {" "}
                      <Link to={gigLink}> Go to offer </Link>
                    </td>
                    <td>{request.offer.title}</td>
                    <td>{request.totalPrice}</td>
                    <td
                      className={
                        request.status === "PENDING" ? "pending" : "completed"
                      }
                    >
                      {request.status}
                    </td>
                    <td>
                      {day} {monthNames[month - 1]} {year}
                    </td>
                    <td>{request?.offer?.traveler.user.username}</td>
                    <td>
                      <img className="message" src="./img/message.png" alt="" />
                    </td>
                    <td width={"75px"}>
                      <ChakraProvider>
                        <OrderState
                          role="sender"
                          offerId={request.offer.id}
                          requestId={request.id}
                        />
                      </ChakraProvider>
                    </td>
                    <td>
                      <ChakraProvider>
                        <Review requestId={request.id} />
                      </ChakraProvider>
                    </td>
                  </tr>
                </tbody>
              );
            })}
        </table>
      </div>
    </div>
  );
};

export default Orders;
