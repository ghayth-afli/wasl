import React, { useEffect, useState } from "react";
import { Slider } from "infinite-react-carousel/lib";
import { hosts } from "../../const";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import "./Gig.scss";

function Gig() {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const [item, setItem] = useState([]);
  const navigate = useNavigate();
  window.scrollTo(0, 0);
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
        if (data.error) {
          setUser(null);
          return;
        }
        setUser(data);
      })
      .catch((err) => {
        setUser(null);
      });
  }, []);

  useEffect(() => {
    fetch(`${hosts.backend}/api/offers/${id}`, {
      method: "GET",
      headers: {
        "Content-Type": "Application/json ; charset=UTF-8",
        // Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setItem(data);
        console.log("data", data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const submitOffer = () => {
    console.log("user", user);
    if (!user) {
      toast.warn("You need to login first!", {
        position: "bottom-right",
        autoClose: 15000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "dark",
      });
      return;
    } else {
      navigate(`/payment-form/${id}`);
    }
  };

  if (!item) {
    toast("🤗 Getting your data ready!", {
      position: "bottom-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "dark",
    });
    return null;
  }

  return (
    <div className="gig">
      <div className="container">
        <div className="left">
          <span className="breadcrumbs"> Transportation offer </span>
          <h1>{item.title}</h1>
          <div className="user">
            <img className="pp" src={item.image} alt="" />
            <span>{item.traveler?.user.username}</span>
            <div className="stars">
              <img src="/img/star.png" alt="" />
              <img src="/img/star.png" alt="" />
              <img src="/img/star.png" alt="" />
              <img src="/img/star.png" alt="" />
              <img src="/img/star.png" alt="" />
              <span>5</span>
            </div>
          </div>
          <Slider slidesToShow={1} arrowsScroll={1} className="slider">
            <img src={item.image} alt="" />
          </Slider>
          <h2>About This Gig</h2>
          <p>{item.description}</p>
          <div className="seller">
            <h2>About The Seller</h2>
            <div className="user">
              <img src={item.image} alt="" />
              <div className="info">
                <span>{item?.travelerName}</span>
                <div className="stars">
                  <img src="/img/star.png" alt="" />
                  <img src="/img/star.png" alt="" />
                  <img src="/img/star.png" alt="" />
                  <img src="/img/star.png" alt="" />
                  <img src="/img/star.png" alt="" />
                  <span>5</span>
                </div>
                <button>Contact Me</button>
              </div>
            </div>
            <div className="box">
              <div className="items">
                <div className="item">
                  <span className="title">From</span>
                  <span className="desc">{item.travelerCountry}</span>


                <div className="item">
                  <span className="title">Languages</span>
                  <span className="desc">English</span>
                </div>
              </div>
              <hr />

              <p>{"About: " + item.travelerBio}</p>


            </div>
          </div>
          <div className="reviews">
            <h2>Reviews</h2>
            <div className="item">
              <div className="user">
                <img
                  className="pp"
                  src="https://images.pexels.com/photos/839586/pexels-photo-839586.jpeg?auto=compress&cs=tinysrgb&w=1600"
                  alt=""
                />
                <div className="info">
                  <span>Garner David</span>
                  <div className="country">
                    <img
                      src="https://fiverr-dev-res.cloudinary.com/general_assets/flags/1f1fa-1f1f8.png"
                      alt=""
                    />
                    <span>United States</span>
                  </div>
                </div>
              </div>
              <div className="stars">
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <span>5</span>
              </div>
              <p>
                I just want to say that art_with_ai was the first, and after
                this, the only artist Ill be using on Fiverr. Communication was
                amazing, each and every day he sent me images that I was free to
                request changes to. They listened, understood, and delivered
                above and beyond my expectations. I absolutely recommend this
                gig, and know already that Ill be using it again very very soon
              </p>
              <div className="helpful">
                <span>Helpful?</span>
                <img src="/img/like.png" alt="" />
                <span>Yes</span>
                <img src="/img/dislike.png" alt="" />
                <span>No</span>
              </div>
            </div>
            <hr />
            <div className="item">
              <div className="user">
                <img
                  className="pp"
                  src="https://images.pexels.com/photos/4124367/pexels-photo-4124367.jpeg?auto=compress&cs=tinysrgb&w=1600"
                  alt=""
                />
                <div className="info">
                  <span>Sidney Owen</span>
                  <div className="country">
                    <img
                      src="https://fiverr-dev-res.cloudinary.com/general_assets/flags/1f1e9-1f1ea.png"
                      alt=""
                    />
                    <span>Germany</span>
                  </div>
                </div>
              </div>
              <div className="stars">
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <span>5</span>
              </div>
              <p>
                The designer took my photo for my book cover to the next level!
                Professionalism and ease of working with designer along with
                punctuality is above industry standards!! Whatever your project
                is, you need this designer!
              </p>
              <div className="helpful">
                <span>Helpful?</span>
                <img src="/img/like.png" alt="" />
                <span>Yes</span>
                <img src="/img/dislike.png" alt="" />
                <span>No</span>
              </div>
            </div>
            <hr />
            <div className="item">
              <div className="user">
                <img
                  className="pp"
                  src="https://images.pexels.com/photos/842980/pexels-photo-842980.jpeg?auto=compress&cs=tinysrgb&w=1600"
                  alt=""
                />
                <div className="info">
                  <span>Lyle Giles </span>
                  <div className="country">
                    <img
                      src="https://fiverr-dev-res.cloudinary.com/general_assets/flags/1f1fa-1f1f8.png"
                      alt=""
                    />
                    <span>United States</span>
                  </div>
                </div>
              </div>
              <div className="stars">
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <img src="/img/star.png" alt="" />
                <span>5</span>
              </div>
              <p>
                Amazing work! Communication was amazing, each and every day he
                sent me images that I was free to request changes to. They
                listened, understood, and delivered above and beyond my
                expectations. I absolutely recommend this gig, and know already
                that Ill be using it again very very soon
              </p>
              <div className="helpful">
                <span>Helpful?</span>
                <img src="/img/like.png" alt="" />
                <span>Yes</span>
                <img src="/img/dislike.png" alt="" />
                <span>No</span>
              </div>
            </div>
          </div>
        </div>
        <div className="right">
          <div className="price">
            <h3>{item.title}</h3>
            <h2> TND {item.price}</h2>
          </div>
          <p>{item.description}</p>
          <div className="details">
            <div className="item">
              <img src="/img/clock.png" alt="" />
              <span>2 Days Delivery</span>
            </div>
            <div className="item">
              <img src="/img/recycle.png" alt="" />
              <span> {new Date(item.date).toLocaleDateString()} </span>
            </div>
          </div>
          <div className="features">
            <div className="item">
              <img src="/img/greencheck.png" alt="" />
              <span>Arrivee {item.destination}</span>
            </div>
            <div className="item">
              <img src="/img/greencheck.png" alt="" />
              <span>Depart {item.depart}</span>
            </div>
          </div>
          <button onClick={submitOffer}>Continue</button>
        </div>
      </div>
    </div>
  );
}

export default Gig;
