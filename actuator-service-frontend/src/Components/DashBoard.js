import React, { useState, useEffect } from "react";
import {
  Card,
  Layout,
  ProgressBar,
  Button,
  Page,
  Label,
  HorizontalGrid,
  Spinner,
  Grid,
  LegacyCard,
  Divider,
  DataTable,
} from "@shopify/polaris";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  PieChart,
  Pie,
  Cell,
} from "recharts";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCheckCircle,
  faExclamationTriangle,
  faExclamationCircle,
  faServer,
} from "@fortawesome/free-solid-svg-icons";

import "./assert/style.css";

export default function Dashboard() {
  const [data, setData] = useState([]);
  const [refreshing, setRefreshing] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setRefreshing(true);
        const response = await axios.get(
          "http://localhost:9001/metrics/processCpuUsage"
        );

        setData(response.data);
        setRefreshing(false);
      } catch (error) {
        console.error("Error:", error);
      }
    };

    fetchData();
  }, []);

  const memoryData = data.measurements * 100;
  console.log(memoryData);

  const memoryUsed = memoryData ? `${Math.round(memoryData)} %` : "Loading...";
  const memoryLeft = memoryData
    ? `${Math.round(100 - memoryData)} %`
    : "Loading...";
  const progressBarFill = memoryData ? memoryData / 1 : 0;
  console.log(progressBarFill);

  const handleRefresh = () => {
    <Spinner accessibilityLabel="Spinner example" size="small" />;
    window.location.reload(false);
  };

  // Example data for the bar chart
  const requestData = [
    { name: "200", count: 20 },
    { name: "404", count: 5 },
    { name: "400", count: 7 },
    { name: "500", count: 15 },
  ];

  // Example data for the pie chart
  const statusData = [
    { name: "200", value: 55 },
    { name: "404", value: 15 },
    { name: "400", value: 20 },
    { name: "500", value: 20 },
  ];

  const COLORS = ["#28a745", "#0088FE", "#FFBB28", "#dc3545"];

  // const renderCards = () => {
  //   if (Array.isArray(data)) {
  //     return data.map((item, index) => (
  //       <Card key={index} sectioned>
  //         <div>
  //           <p>Status Code: {item.statusCode}</p>
  //           <p>Response: {item.response}</p>
  //           <p>Last Updated: {item.lastUpdated}</p>
  //         </div>
  //         <div
  //           style={{
  //             backgroundColor: item.responseColor,
  //             width: "100%",
  //             height: "10px",
  //             marginTop: "10px",
  //           }}
  //         />
  //       </Card>
  //     ));
  //   } else {
  //     return <p>No data available.</p>;
  //   }
  // };
  function getCurrentTime() {
    const currentTime = new Date();
    const formattedTime = currentTime.toLocaleTimeString(); // Adjust the format as desired
    return formattedTime;
  }

  return (
    <Page>
      <Layout>
        <Layout.Section>
          <Card>
            <Label>
              <b>Process CPU Usage</b>
            </Label>
            <div
              style={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
              }}
            >
              <div>
                <p style={{ marginBottom: "5px" }}>Used</p>
                <p
                  style={{
                    fontSize: "24px",
                    fontWeight: "bold",
                    marginBottom: "5px",
                  }}
                >
                  {memoryUsed}
                </p>
              </div>
              <div>
                <p style={{ marginBottom: "5px" }}>Left</p>
                <p
                  style={{
                    fontSize: "24px",
                    fontWeight: "bold",
                    marginBottom: "5px",
                  }}
                >
                  {memoryLeft}
                </p>
              </div>
              <Button disabled={refreshing} onClick={handleRefresh}>
                {refreshing ? "Refreshing..." : "Refresh"}
              </Button>
            </div>
            <ProgressBar
              progress={progressBarFill}
              size="small"
              style={{
                margin: "10px 0",
              }}
            />
          </Card>
        </Layout.Section>
        <Layout.Section>
          <Card title="responses">
            <div className="container">
              <div className="card-body">
                <h4 className="card-title">
                  <span className="response-text">
                    <b>Http Response</b>
                  </span>
                </h4>

                <div className="row">
                  <div className="col-sm-6 col-md-6 col-lg-3 col-xl-3">
                    <div className="card success-card">
                      <div className="card-body">
                        <div className="card-response-data-circle">55</div>
                        <h6 className="card-title">
                          <FontAwesomeIcon icon={faCheckCircle} />
                          <span className="response-text">200 Responses</span>
                        </h6>
                        <p className="card-text">Updated: {getCurrentTime()}</p>
                      </div>
                    </div>
                  </div>

                  <div className="col-sm-6 col-md-6 col-lg-3 col-xl-3">
                    <div className="card error-card">
                      <div className="card-body">
                        <div className="card-response-data-circle">15</div>
                        <h6 className="card-title">
                          <FontAwesomeIcon icon={faExclamationCircle} />
                          <span className="response-text">404 Responses</span>
                        </h6>
                        <p className="card-text">
                          Updated : {getCurrentTime()}
                        </p>
                      </div>
                    </div>
                  </div>
                  <div className="col-sm-6 col-md-6 col-lg-3 col-xl-3">
                    <div className="card warning-card">
                      <div className="card-body">
                        <div className="card-response-data-circle">20</div>
                        <h6 className="card-title">
                          <FontAwesomeIcon icon={faExclamationTriangle} />
                          <span className="response-text">400 Responses</span>
                        </h6>
                        <p className="card-text">
                          Updated : {getCurrentTime()}
                        </p>
                      </div>
                    </div>
                  </div>
                  <div className="col-sm-6 col-md-6 col-lg-3 col-xl-3">
                    <div className="card info-card">
                      <div className="card-body">
                        <div className="card-response-data-circle">20</div>
                        <h6 className="card-title">
                          <FontAwesomeIcon icon={faServer} />
                          <span className="response-text">500 Responses</span>
                        </h6>
                        <p className="card-text">
                          Updated : {getCurrentTime()}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </Card>
        </Layout.Section>

        <Layout.Section>
          <Card title="Request Count">
            <div className="container">
              <div className="card-body">
                <h4 className="card-title">
                  <span className="response-text">
                    <b>Http Response</b>
                  </span>
                </h4>
                <HorizontalGrid gap="4" columns={2}>
                  <div style={{ marginTop: "20px" }}>
                    <BarChart width={500} height={300} data={requestData}>
                      <CartesianGrid strokeDasharray="3 3" />
                      <XAxis dataKey="name" />
                      <YAxis nameKey="value" />
                      <Tooltip />
                      <Legend />
                      <Bar dataKey="count" fill="#8884d8" label>
                        {requestData.map((entry, index) => (
                          <Cell
                            key={`cell-${index}`}
                            fill={COLORS[index % COLORS.length]}
                          />
                        ))}
                      </Bar>
                    </BarChart>
                  </div>
                  <div style={{ marginTop: "20px" }}>
                    <PieChart width={500} height={300}>
                      <Pie
                        data={statusData}
                        dataKey="value"
                        nameKey="name"
                        cx="50%"
                        cy="50%"
                        outerRadius={80}
                        fill="#8884d8"
                        label
                      >
                        {statusData.map((entry, index) => (
                          <Cell
                            key={`cell-${index}`}
                            fill={COLORS[index % COLORS.length]}
                          />
                        ))}
                      </Pie>
                      <Legend />
                    </PieChart>
                  </div>
                </HorizontalGrid>
              </div>
            </div>
          </Card>
        </Layout.Section>

        <Layout.Section>
          <Card title="http-traces">
            <div className="container">
              <div className="card-body">
                <h4 className="card-title">
                  <span className="response-text">
                    <b>Http Traces</b>
                  </span>
                </h4>
                <DataTable
                  columnContentTypes={[
                    "text",
                    "text",
                    "numeric",
                    "text",
                    "text",
                    "text",
                  ]}
                  headings={[
                    "Timestamp",
                    "Method",
                    "Time Taken (ms)",
                    "Status",
                    "URI",
                    "View",
                  ]}
                  rows={[]}
                  //{httpTraces.map((trace) => [
                  //   trace.timestamp,
                  //   trace.method,
                  //   trace.timeTaken,
                  //   trace.status,
                  //   trace.uri,
                  //   <Button onClick={() => handleView(trace)}>View</Button>,
                  // ])}
                />
              </div>
            </div>
          </Card>
        </Layout.Section>
      </Layout>
    </Page>
  );
}
